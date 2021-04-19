package comm.file;

public class Result {
    private Boolean found;
    private String path;

    public Result() {
        found = false;
        path = null;
    }

    public void setFound(Boolean found) {
        this.found = found;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Boolean isFound() {
        return found;
    }
}
