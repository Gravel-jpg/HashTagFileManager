
//I want the act of creating new Tags to be a little obtuse, so that the system always works well.
//Whenever selecting tags (searching files based on tags, choosing tag to apply to file) you should always have to select from a
//autofill dropdown menu. That way there is never any misinterpreted tags.

 public class Tag {
    private final String name;


//    Constructor
    public Tag(String initName){
        name = initName;
    }


//    Overrides
     @Override
    public boolean equals(Object obj){
//        In order to override Java's equals function, the signature needs to be the same. Hence the use ob Object obj
        if (this == obj) return true;
//        Casts the obj to Tag and assigns it a variable name
        if (!(obj instanceof Tag other)) return false;
        return this.name.equals(other.name);
    }
    @Override
    public int hashCode(){
        return name.hashCode();
    }


//    Getters and Setters
    public String getName(){return name;}
//     We can bring this function back, at the cost of making name not final. To me, this seems fine now. Might change in the future.
//    public void setName(String setName){name = setName;}
}
