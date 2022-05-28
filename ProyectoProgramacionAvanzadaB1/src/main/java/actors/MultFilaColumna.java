package actors;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import akka.japi.pf.ReceiveBuilder;

public class MultFilaColumna extends AbstractActor {

    public static Props props(){
        return Props.create(MultFilaColumna.class);
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TaskCalcElement.class, task -> {
                    task.calcValue();
                    sender().tell(task, self());
                }).build();
    }
}
