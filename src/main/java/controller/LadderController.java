package controller;

import domain.model.consequence.Consequence;
import domain.model.consequence.Consequences;
import domain.model.ladder.Height;
import domain.model.ladder.Ladder;
import domain.model.ladder.LadderGame;
import domain.model.ladder.Result;
import domain.model.participant.People;
import domain.model.participant.Person;
import handler.ExceptionHandler;
import utils.RuleGeneratorImpl;
import view.InputView;
import view.ResultView;

import java.util.List;
import java.util.function.Supplier;

public class LadderController {

    private final ResultView resultView = new ResultView();
    private final InputView inputView = new InputView();
    private final ExceptionHandler handler = new ExceptionHandler();

    public void execute() {
        People people = registerPeople();
        Consequences consequences = registerConsequences(people);
        Ladder ladder = makeLadder(people);
        LadderGame ladderGame = new LadderGame(ladder, people, consequences);

        resultView.printLadderGame(people, ladder, consequences);
        Result result = ladderGame.play();
        showResult(result, people);
    }

    private void showResult(Result result, People people) {
        String chosen = askChosen(people);
        if (chosen.equals("all")) {
            resultView.printAll(result);
            return;
        }
        resultView.printResult(result, new Person(chosen));
        showResult(result, people);
    }


    private String askChosen(People people) {
        Supplier<String> chosenSupplier = () -> {
            String chosen = inputView.askChosen();
            return people.findProperParticipant(chosen);
        };
        return registerWithRetry(chosenSupplier);
    }

    private Ladder makeLadder(People people) {
        Supplier<Ladder> ladderSupplier = () -> {
            String inputHeight = inputView.askLadderHeight();
            Height height = new Height(inputHeight);
            return new Ladder(height, people.getNumberOfParticipants(), new RuleGeneratorImpl());
        };
        return registerWithRetry(ladderSupplier);
    }

    private People registerPeople() {
        Supplier<People> peopleSupplier = () -> {
            List<String> participants = inputView.askParticipants();
            return new People(participants);
        };
        return registerWithRetry(peopleSupplier);
    }

    private Consequences registerConsequences(People people) {
        Supplier<Consequences> consequencesSupplier = () -> {
            List<String> consequences = inputView.askConsequences();
            return new Consequences(consequences, people.getNumberOfParticipants());
        };
        return registerWithRetry(consequencesSupplier);
    }

    private <T> T registerWithRetry(Supplier<T> callback) {
        return handler.handle(callback);
    }
}
