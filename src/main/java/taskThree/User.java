package taskThree;

class User {
    private Long userId;
    private Long id;
    private String title;
    private boolean completed;

    public Long getUserId() {
        return userId;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return
                "\n" + title;
    }
}
