const { I, Wordlist } = inject();

Feature('Answers');

Scenario('Answer to a question', (I, Wordlist) => {
    I.registerRandomUser();

    I.amOnPage(Wordlist.pageUrl.askQuestion);

    I.click("//div[contains(@class,'card')]");

    I.wait(5);

    I.fillField('answerBody','answer');
    I.click('post answer');
    I.wait(5);
    I.see('answer');

    I.fillField('commentBody','comment');
    I.click('Post');
    I.see('comment');

});
