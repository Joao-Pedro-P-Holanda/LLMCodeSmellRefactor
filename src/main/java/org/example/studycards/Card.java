package org.example.studycards;

import java.util.Objects;

// Method extract

public class Card {

    private String question;
    private String answer;

    public Card(String question, String answer) {
        this.question = Objects.requireNonNull(question, "Question cannot be null");
        this.answer = Objects.requireNonNull(answer, "Answer cannot be null");
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Card withQuestion(String question) {
        return new Card(question, this.answer);
    }

    public Card withAnswer(String answer) {
        return new Card(this.question, answer);
    }

    public String formatResponse(Integer cardId) {
        return "[" + cardId + "] " +
                "The random question was: " + this.getQuestion() + " | " +
                "The answer is: " + this.getAnswer();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(question, card.question) && Objects.equals(answer, card.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, answer);
    }

    public void edit(String question, String answer) {
        setQuestion(question);
        setAnswer(answer);
    }
}