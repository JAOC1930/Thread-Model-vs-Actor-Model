package actors;

import akka.actor.*;
import akka.japi.pf.ReceiveBuilder;

public class MultMatriz extends AbstractActor {
    Matriz matriz1;
    Matriz matriz2;

    public static Props props(){
        return Props.create(MultMatriz.class);
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(Matriz.class, matriz -> {

                    if (this.matriz1 == null) {
                        this.matriz1 = matriz;
                    } else {

                        this.matriz2 = matriz;
                        int sizeM1 = this.matriz1.getValues().length;
                        int sizeM2 = this.matriz2.getValues()[0].length;


                        if (sizeM1 == sizeM2) {

                            int [][] output = new int[sizeM1][sizeM2];

                            ActorSystem actorSystem = ActorSystem.create();
                            ActorRef printActor = actorSystem.actorOf(ImprimirMatriz.props(), "Actor2");
                            printActor.tell(new Matriz(output), self());

                            for (var i = 0; i < output.length; i++) {

                                for (var j = 0; j < output[0].length; j++) {
                                    ActorRef taskActor = actorSystem.actorOf(MultFilaColumna.props(), "ActorFila"+i+"Columna"+j);
                                    taskActor.tell(new TaskCalcElement(this.matriz1, this.matriz2, i, j), printActor);
                                }
                            }
                        }
                    }
                }).build();
    }
}
