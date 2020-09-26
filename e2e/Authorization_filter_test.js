Feature('Authorization filter');

Scenario('Test redirection to login Page', (I) => {
  I.amOnPage('/private');
  // The authorization filter must show me the homepage if I am not logged
  I.see('username')
  // pause();
});
