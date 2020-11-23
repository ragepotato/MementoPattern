public class Memento {
    private String state;

    public Memento(String state){  //maintains the state of the memento, parameters are all values you want to save
        this.state = state;
    }

    public String getState(){
        return state;
    }
}