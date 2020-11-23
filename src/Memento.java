public class Memento {
    private String state;

    public Memento(String state){  //maintains the state of the memento
        this.state = state;
    }

    public String getState(){
        return state;
    }
}