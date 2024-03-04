package domain.model.ladder;

import domain.model.consequence.Consequence;
import domain.model.consequence.Consequences;
import domain.model.participant.People;
import domain.model.participant.Person;

public class LadderGame {
    private final Ladder ladder;
    private final People people;
    private final Consequences consequences;

    public LadderGame(Ladder ladder, People people, Consequences consequences) {
        this.ladder = ladder;
        this.people = people;
        this.consequences = consequences;
    }

    public Result play() {
        Result result = new Result();
        for (int positionOfPerson = 0; positionOfPerson < people.getNumberOfParticipants(); positionOfPerson++) {
            int positionOfConsequence = ladder.goToConsequence(positionOfPerson, 0);
            Person person = people.getNameByOrder(positionOfPerson);
            Consequence consequence = consequences.getConsequenceByOrder(positionOfConsequence);
            result.make(person, consequence);
        }
        return result;
    }

}
