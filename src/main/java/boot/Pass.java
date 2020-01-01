/*
 * Decompiled with CFR 0_123.
 */
package boot;

import com.sqlite.model.Order;
import java.util.HashSet;
import java.util.Set;
import model.TagInfo;

public class Pass {
    private String state;
    private Set<TagInfo> tagInfos = new HashSet<TagInfo>();
    private int passCount;
    private long diff;
    private boolean isLeading = false;
    private boolean isLastPassWrong = false;
    private boolean isMissPass = false;
    private boolean isSkuChange = false;
    private boolean isStrikeout = false;
    private boolean needConfirm = false;
    private String timeStr;
    private long rowId;
    private int correctiveSku;
    private int wrongIndex;
    private TagInfo tagInfo = new TagInfo();
    private boolean isEndOfOrder;
    private boolean lastPassEndOfOrder;
    private boolean isEmpty;
    private String orderNum;
    private String orderNumFrom;
    private Order order;
    
    private String actionCode;
    
    private String lastEpc;
    
    public String getLastEpc() {
		return lastEpc;
	}

	public void setLastEpc(String lastEpc) {
		this.lastEpc = lastEpc;
	}

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	private boolean isTrueStrikeOut;
    
    public boolean isTrueStrikeOut() {
		return isTrueStrikeOut;
	}

	public void setTrueStrikeOut(boolean isTrueStrikeOut) {
		this.isTrueStrikeOut = isTrueStrikeOut;
	}

	private int sku;

    public long getDiff() {
        return this.diff;
    }

    public void setDiff(long diff) {
        this.diff = diff;
    }

    public boolean isMissPass() {
        return this.isMissPass;
    }

    public void setMissPass(boolean isMissPass) {
        this.isMissPass = isMissPass;
    }

    public String getTimeStr() {
        return this.timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }

    public long getRowId() {
        return this.rowId;
    }

    public void setRowId(long rowId) {
        this.rowId = rowId;
    }

    public boolean isNeedConfirm() {
        return this.needConfirm;
    }

    public void setNeedConfirm(boolean needConfirm) {
        this.needConfirm = needConfirm;
    }

    public boolean isStrikeout() {
        return this.isStrikeout;
    }

    public void setLastPassStrikedout(boolean isStrikeout) {
        this.isStrikeout = isStrikeout;
    }

    public TagInfo getTagInfo() {
        return this.tagInfo;
    }

    public void setTagInfo(TagInfo tagInfo) {
        this.tagInfo = tagInfo;
    }

    public int getWrongIndex() {
        return this.wrongIndex;
    }

    public void setWrongIndex(int wrongIndex) {
        this.wrongIndex = wrongIndex;
    }

    public int getCorrectiveSku() {
        return this.correctiveSku;
    }

    public void setCorrectiveSku(int correctiveSku) {
        this.correctiveSku = correctiveSku;
    }

    public boolean isSkuChange() {
        return this.isSkuChange;
    }

    public void setSkuChange(boolean isSkuChange) {
        this.isSkuChange = isSkuChange;
    }

    public boolean isLastPassWrong() {
        return this.isLastPassWrong;
    }

    public void setLastPassWrong(boolean isLastPassWrong) {
        this.isLastPassWrong = isLastPassWrong;
    }

    public boolean isLastPassEndOfOrder() {
        return this.lastPassEndOfOrder;
    }

    public void setLastPassEndOfOrder(boolean lastPassEndOfOrder) {
        this.lastPassEndOfOrder = lastPassEndOfOrder;
    }

    public boolean isEmpty() {
        return this.isEmpty;
    }

    public void setEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }

    public String getOrderNumFrom() {
        return this.orderNumFrom;
    }

    public void setOrderNumFrom(String orderNumFrom) {
        this.orderNumFrom = orderNumFrom;
    }

    public Order getOrder() {
        return this.order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getOrderNum() {
        return this.orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public boolean isEndOfOrder() {
        return this.isEndOfOrder;
    }

    public void setIsOrderStarted(boolean isEndOfOrder) {
        this.isEndOfOrder = isEndOfOrder;
    }

    public int getSku() {
        return this.sku;
    }

    public void setSku(int sku) {
        this.sku = sku;
    }

    public boolean isLeading() {
        return this.isLeading;
    }

    public void setLeading(boolean isLeading) {
        this.isLeading = isLeading;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Set<TagInfo> getTagInfos() {
        return this.tagInfos;
    }

    public void setTagInfos(Set<TagInfo> tagInfos) {
        this.tagInfos = tagInfos;
    }

    public int getPassCount() {
        return this.passCount;
    }

    public void setPassCount(int passCount) {
        this.passCount = passCount;
    }
}

