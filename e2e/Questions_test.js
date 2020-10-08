const { I, Wordlist } = inject();

Feature('Questions');

Scenario('Visit the Question Page', (I, Wordlist) => {
    I.amOnPage(Wordlist.pageUrl.askQuestion);
    I.see('Ask your question')
});

Scenario('Submit a Question', (I, Wordlist) => {
    I.registerRandomUser();
    I.amOnPage(Wordlist.pageUrl.askQuestion);

    I.fillField('label','aaaa');
    I.fillField('content','aaa');
    I.click(Wordlist.formField.SubmitButton);
    I.wait(1);
    I.see('aaaa');

});
