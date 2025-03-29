
//I want creating new Tags to be a little obtuse, so that the system always works well.
//Whenever selecting tags (searching files based on tags, choosing tag to apply to file) you should always have to select from a
//autofill dropdown menu. That way there is never any misinterpreted tags.

 public class Tag {
    private String name;

    public Tag(String initName){
        name = initName;
    }


//    Getters and Setters
    public String getName(){return name;}
    public void setName(String setName){name = setName;}
}
