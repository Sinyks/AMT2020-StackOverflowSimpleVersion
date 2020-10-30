const { I, Wordlist } = inject();

Feature('Questions');

Scenario('Visit the Question Page wile not logged', (I, Wordlist) => {
    I.amOnPage(Wordlist.pageUrl.askQuestion);
    I.dontSee('Ask your question');
});

Scenario('Visit the Question Page wile logged', (I, Wordlist) => {
    I.registerRandomUser();
    I.amOnPage(Wordlist.pageUrl.askQuestion);
    I.see('Ask your question');
    I.wait(1);
    I.askQuestion();
    I.see(Wordlist.Data.questionContent);
});

