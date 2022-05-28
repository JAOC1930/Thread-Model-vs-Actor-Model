package actors;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;

public class ImprimirMatriz extends AbstractActor {
    Matriz matriz;
    int numImpreso;

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(Matriz.class, matriz -> {
                    this.matriz = matriz;
                    numImpreso = matriz.size();
                })
                .match(TaskCalcElement.class, task -> {
                    matriz.getValues()[task.getRowIndex()][task.getCloIndex()] = task.getElement();
                    numImpreso --;

                    if(numImpreso == 0){
                        System.out.println(matriz.toString());
                    }
                })
                .build();
    }

    public static Props props(){
        return Props.create(ImprimirMatriz.class);
    }
}
