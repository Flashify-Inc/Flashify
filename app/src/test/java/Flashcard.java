public class Flashcard {
    private String frontSide;
    private String backSide;
    private Flashcard nextFlashcard;
    private Flashcard previousFlashcard;
    //private string id;

    //constructor
    public Flashcard(String frontSide, String backSide){
        this.frontSide = frontSide;
        this.backSide = backSide;
        //this.nextFlashcard = null;
        //this.previousFlashcard = null;
    }

    //setter function for front side of the flashcard
    public void setFrontSide(String frontSide){
        this.frontSide = frontSide;
    }

    //getter function for the front side of the flashcard
    public String getFrontSide () {
        return this.frontSide;
    }

    //setter function for the back side of the flashcard
    public void setBackSide(String backSide){
        this.backSide = backSide;
    }

    public String getBackSide(){
        return this.backSide;
    }

    //public void updatePosition(){}

}
