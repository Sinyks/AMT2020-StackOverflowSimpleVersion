const { I, Wordlist } = inject();

Feature('Questions');

Scenario('Visit the Question Page wile logged', (I, Wordlist) => {
    I.amOnPage(Wordlist.pageUrl.askQuestion);
    I.see('Others asked these questions');
});

Scenario('Visit the Question Page wile logged', (I, Wordlist) => {
    I.login('rock','1234')
    I.amOnPage(Wordlist.pageUrl.askQuestion);
    I.see('Ask your question');
});

Scenario('Submit a Question', (I, Wordlist) => {
    I.amOnPage(Wordlist.pageUrl.askQuestion);
    I.askQuestions();
    I.wait(1);
    I.see(Wordlist.Data.questionLabel);
});
