package scs2682.finalproject.ui.home;

public class NoteAdapterPage {
    private final int layoutId;
    private final String title;

    public NoteAdapterPage(int layoutId, String title) {
        this.layoutId = layoutId;
        this.title = title;
    }

    public int getLayoutId() {
        return layoutId;
    }

    public String getTitle() {
        return title;
    }
}
