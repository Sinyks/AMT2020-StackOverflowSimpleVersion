Feature('Homepage');

Scenario('Visit the Homepage', (I) => {
  I.amOnPage('/');
  I.see('Welcome');
  // pause();
});
