const { I, Wordlist } = inject();


Feature('Comments');

Scenario('Comment a Question', (I, Wordlist) => {
    I.registerRandomUser();

    I.amOnPage(Wordlist.pageUrl.askQuestion);

    I.click("//div[contains(@class,'card')]");

    const commentContent = Wordlist.Data.commentContent;
    I.fillField('commentBody',commentContent);
    I.click('Comment');
    I.wait(1);
    I.see(commentContent);
});
