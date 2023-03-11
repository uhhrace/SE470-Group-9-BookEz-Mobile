package Objects;

//class containing id for entire path and the path 
public class pathObject {

    private int id;
    private String path;

    /**
     * Consturctor for path object
     * @param id path number in the order it was uploaded
     * @param path path name on local device
     */
    public pathObject(int id, String path){

        this.id = id;
        this.path = path;
    }

    //setter and getter methods
    public void setID(int id){
        this.id = id;
    }

    public int getID(){
        return id;
    }

    public void setpath(String path){
        this.path = path;
    }

    public String getPath(){
        return path;
    }
}

