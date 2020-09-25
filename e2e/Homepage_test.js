Feature('Homepage');

Scenario('test something', (I) => {
  I.amOnPage('http://localhost:8080/stackoverflow-simplified/');
  I.see('Welcome');
});
