const { I, Wordlist } = inject();

// We will use chanceJS to generate username and password pseudo-randomly

Feature('Register');

Scenario('Check access to register page',(I, Wordlist) =>{
    I.amOnPage(Wordlist.pageUrl.register)
    I.seeCurrentUrlEquals(Wordlist.pageUrl.register)
    // I.see('Register')
});

Scenario('Check Form and form method', (I, Wordlist) =>{
    I.amOnPage(Wordlist.pageUrl.register)
    I.seeAttributesOnElements('//form', { method: "post"});
});

Scenario('Check register with correct attribute', (I, Wordlist) => {
    I.amOnPage(Wordlist.pageUrl.register)
    I.registerRandomUser();

    /** TODO
     * Page must return the Home page with logged user
     */
});

/**
 * TODO: 
 * check submit with uncorrect password
 * check sumbit with empty username
 * check submit with already existing user
 */