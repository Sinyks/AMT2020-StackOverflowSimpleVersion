const { I, Wordlist } = inject();

Feature('login');

Scenario('Check Access to login page', (I, Wordlist) => {
    I.amOnPage(Wordlist.pageUrl.login);
    // I.seeCurrentUrlEquals(Wordlist.pageUrl.login)
    I.see('Login')
});

Scenario('Check Form and form method', (I, Wordlist) =>{
    I.amOnPage(Wordlist.pageUrl.login);
    I.seeAttributesOnElements('//form', { method: "post"});
});

Scenario('Check form submit non-existing account',(I, Wordlist) => {
    I.amOnPage(Wordlist.pageUrl.login);
    I.login('Null','Null');

    /**
     * Error message must be returned (To Implement)
     */
});

/**
 * TODO:
 * 
 * Check login with existing account
 * Check login with 
 */
