const { I, Wordlist } = inject();

Feature('Questions');

Scenario('Visit the Question Page', (I, Wordlist) => {
    I.amOnPage(Wordlist.pageUrl.askQuestion);
    I.see('Ask your question')
});

Scenario('Submit a Question', (I, Wordlist) => {
    I.registerRandomUser();
    I.amOnPage(Wordlist.pageUrl.askQuestion);

    I.fillField(Wordlist.formField.Qlabel,Wordlist.Data.questionLabel);
    I.fillField(Wordlist.formField.Qcontent,Wordlist.Data.questionContent);
    I.click(Wordlist.formField.SubmitButton);
    I.wait(1);
    I.see(Wordlist.Data.questionLabel);

});
