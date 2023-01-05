
//class containing id for entire path and the path 
public class pathObject {

    private int id;
    private String path;

    //consturctor for object text
    public pathObject(int id, String path){

        this.id = id;
        this.path = path;
    }//end of pathObject constructor

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

}//end of pathObject class

