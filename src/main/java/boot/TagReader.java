/*
 * Decompiled with CFR 0_123.
 */
package boot;

import boot.Pass;
import boot.TagInfoConsumer;
import java.util.HashSet;
import java.util.Set;

public class TagReader {
    Set<TagInfoConsumer> tagInfoConsumers = new HashSet<TagInfoConsumer>();

    public void addTagInfoConsumer(TagInfoConsumer tagInfoConsumer) {
        this.tagInfoConsumers.add(tagInfoConsumer);
    }

    public void onPass(Pass pass) {
        for (TagInfoConsumer tagInfoConsumer : this.tagInfoConsumers) {
            tagInfoConsumer.onPass(pass);
        }
    }
}

