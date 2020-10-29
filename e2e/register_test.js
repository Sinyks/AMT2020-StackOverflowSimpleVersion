const { I, Wordlist } = inject();

Feature('Register');

Scenario('Check access to register page',(I, Wordlist) =>{
    I.amOnPage(Wordlist.pageUrl.register)
    // I.seeCurrentUrlEquals(Wordlist.pageUrl.register)
    I.see('Register')
});

Scenario('Check Form and form method', (I, Wordlist) =>{
    I.amOnPage(Wordlist.pageUrl.register)
    I.seeAttributesOnElements('//form', { method: "post"});
});

Scenario('Check register with correct attribute', (I, Wordlist) => {
    I.registerRandomUser();
    I.seeInCurrentUrl(Wordlist.Data.root);
});

