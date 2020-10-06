const { I, Wordlist } = inject();

Feature('Questions');

Scenario('Visit the Question Page', (I, Wordlist) => {
    I.amOnPage(Wordlist.pageUrl.askQuestion);
    I.see('Ask your question')
});

Scenario('Submit a Question', (I, Worklist) => {
    
});
