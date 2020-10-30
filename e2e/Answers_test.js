const { I, Wordlist } = inject();

Feature('Answers');

Scenario('Answer to a question', (I, Wordlist) => {
    I.registerRandomUser();

    I.amOnPage(Wordlist.pageUrl.askQuestion);

    I.click("//div[contains(@class,'card')]");

    I.wait(5);
    const answerContent = Wordlist.Data.answerContent;

    I.fillField('answerBody',answerContent);
    I.click('Answer');
    I.wait(1);
    I.see(answerContent);
});
