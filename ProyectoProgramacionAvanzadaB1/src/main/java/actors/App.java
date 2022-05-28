package actors;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

public class App {
    public static void main(String[] args) {
        int[][] mat1Values = {
                {1, 2},
                {3, 4},
                {5, 6},
        };
        Matriz m1 = new Matriz(mat1Values);
        int[][] mat2Values = {
                {1, 2, 3},
                {3, 4, 5}
        };
        Matriz m2 = new Matriz(mat2Values);

        ActorSystem actorSystem = ActorSystem.create();
        ActorRef taskActor = actorSystem.actorOf(MultMatriz.props(), "Actor1");
        taskActor.tell(m1, ActorRef.noSender());
        taskActor.tell(m2, ActorRef.noSender());
    }
}
