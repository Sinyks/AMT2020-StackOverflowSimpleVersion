const { I, Wordlist } = inject();

Feature('Authorization filter');

Scenario('restricted access to private page redirect to login Page', (I, Wordlist) => {
  I.amOnPage(Wordlist.pageUrl.private);
  I.wait(2);
  I.seeInCurrentUrl(Wordlist.pageUrl.login)
  // pause();
});
