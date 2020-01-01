package com.sqlite.utils;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Map;

public class FileWatcher {
    private String root;
    private Map<Kind, FileEvent> mp;
	private WatchService watchService;
	private boolean isClosed;
    
	/*public static void main(String[] args) throws IOException {
		String root = "c:\\filewatchtest\\";
		
		Map<Kind, FileEvent> mp = new HashMap<>();
		mp.put(StandardWatchEventKinds.ENTRY_CREATE, new FileEvent() {
			
			@Override
			public void on(Path path, WatchService watchService) throws IOException {
				String dest = "c:\\dest\\" + path.getFileName();
				Files.copy(path, Paths.get(dest));
			}
		});
		
		watch(root, mp);
	}*/

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	public Map<Kind, FileEvent> getMp() {
		return mp;
	}

	public void setMp(Map<Kind, FileEvent> mp) {
		this.mp = mp;
	}
    
	public void close(){
		try {
			watchService.close();
			isClosed = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public  void watch() {
		isClosed = false;
		try {
			
			watchService = FileSystems.getDefault().newWatchService();
			Path tmpPath = Paths.get(root);
			tmpPath.register(watchService, mp.keySet().toArray(new Kind[] {}));
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		for (;;) {
			if(isClosed) return;
			//System.out.println("polling " + this.root);
			try {
				WatchKey key = watchService.take();
				for (WatchEvent<?> event : key.pollEvents()) {
					Kind<?> kind = event.kind();

					Path dir = (Path) key.watchable();
					Path fullPath = dir.resolve((Path) event.context());
					FileEvent fileEvent = mp.get(kind);
					fileEvent.on(fullPath, watchService);
				}
				
				// reset is invoked to put the key back to ready state
				boolean valid = key.reset();
			
				if (!valid) {
					break;
				}
				
			} catch (Exception e) {
				//System.out.println("pause file watcher!");
			}
		}
	}
	
	public interface FileEvent {
		void on(Path path, WatchService watchService) throws IOException;
	}
}
