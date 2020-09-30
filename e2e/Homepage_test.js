const { I, Wordlist } = inject();

Feature('Homepage');

Scenario('Visit the Homepage', (I, Wordlist) => {
  I.amOnPage(Wordlist.pageUrl.root);
  I.see('Welcome');
  // pause();
});

Scenario('Click on \"Browse Questions\" Button must redirect me to Questions',(I, Wordlist) => {
  I.amOnPage(Wordlist.pageUrl.root);
  I.click("Browse questions");
  /**
   * controle questions page
   */

});
