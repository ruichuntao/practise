package sohu.danmuku.model;


public class DanmakuEntity {

    private String text; // 文本
    private boolean isMine = false; // 是否自己发送
    private int index = 0;// 轨道
    private int order = 0; // 优先级顺序

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
