memberSearchIndex = [{"p":"com.fdmgroup.javaproject.model","c":"Account","l":"Account()","u":"%3Cinit%3E()"},{"p":"com.fdmgroup.javaproject.model","c":"Account","l":"Account(String, double, User)","u":"%3Cinit%3E(java.lang.String,double,com.fdmgroup.javaproject.model.User)"},{"p":"com.fdmgroup.javaproject.controller","c":"AccountController","l":"AccountController()","u":"%3Cinit%3E()"},{"p":"com.fdmgroup.javaproject.controller","c":"AccountController","l":"accounts(Model, HttpSession)","u":"accounts(org.springframework.ui.Model,jakarta.servlet.http.HttpSession)"},{"p":"com.fdmgroup.javaproject.service","c":"AccountService","l":"AccountService()","u":"%3Cinit%3E()"},{"p":"com.fdmgroup.javaproject.model","c":"Budget","l":"Budget()","u":"%3Cinit%3E()"},{"p":"com.fdmgroup.javaproject.model","c":"Budget","l":"Budget(LocalDate, LocalDate, double, User, Category)","u":"%3Cinit%3E(java.time.LocalDate,java.time.LocalDate,double,com.fdmgroup.javaproject.model.User,com.fdmgroup.javaproject.model.Category)"},{"p":"com.fdmgroup.javaproject.controller","c":"BudgetController","l":"BudgetController()","u":"%3Cinit%3E()"},{"p":"com.fdmgroup.javaproject.controller","c":"BudgetController","l":"budgets(Integer, Integer, Model, HttpSession)","u":"budgets(java.lang.Integer,java.lang.Integer,org.springframework.ui.Model,jakarta.servlet.http.HttpSession)"},{"p":"com.fdmgroup.javaproject.service","c":"BudgetService","l":"BudgetService()","u":"%3Cinit%3E()"},{"p":"com.fdmgroup.javaproject.model","c":"Category","l":"Category()","u":"%3Cinit%3E()"},{"p":"com.fdmgroup.javaproject.model","c":"Category","l":"Category(String)","u":"%3Cinit%3E(java.lang.String)"},{"p":"com.fdmgroup.javaproject.controller","c":"CategoryController","l":"CategoryController()","u":"%3Cinit%3E()"},{"p":"com.fdmgroup.javaproject.service","c":"CategoryService","l":"CategoryService()","u":"%3Cinit%3E()"},{"p":"com.fdmgroup.javaproject.controller","c":"AccountController","l":"createAccount(String, double, HttpSession)","u":"createAccount(java.lang.String,double,jakarta.servlet.http.HttpSession)"},{"p":"com.fdmgroup.javaproject.service","c":"BudgetService","l":"createBudget(Budget)","u":"createBudget(com.fdmgroup.javaproject.model.Budget)"},{"p":"com.fdmgroup.javaproject.service","c":"CategoryService","l":"createCategory(String)","u":"createCategory(java.lang.String)"},{"p":"com.fdmgroup.javaproject.service","c":"AccountService","l":"createNewAccount(Account)","u":"createNewAccount(com.fdmgroup.javaproject.model.Account)"},{"p":"com.fdmgroup.javaproject.service","c":"TransactionService","l":"createTransaction(Transaction)","u":"createTransaction(com.fdmgroup.javaproject.model.Transaction)"},{"p":"com.fdmgroup.javaproject.controller","c":"DashboardController","l":"dashboard(HttpSession, Model)","u":"dashboard(jakarta.servlet.http.HttpSession,org.springframework.ui.Model)"},{"p":"com.fdmgroup.javaproject.controller","c":"DashboardController","l":"DashboardController()","u":"%3Cinit%3E()"},{"p":"com.fdmgroup.javaproject.controller","c":"AccountController","l":"deleteAccount(int)"},{"p":"com.fdmgroup.javaproject.service","c":"AccountService","l":"deleteAccountById(int)"},{"p":"com.fdmgroup.javaproject.controller","c":"BudgetController","l":"deleteBudget(int)"},{"p":"com.fdmgroup.javaproject.service","c":"BudgetService","l":"deleteBudgetById(int)"},{"p":"com.fdmgroup.javaproject.controller","c":"TransactionController","l":"deleteTransaction(int)"},{"p":"com.fdmgroup.javaproject.service","c":"TransactionService","l":"deleteTransactionById(int)"},{"p":"com.fdmgroup.javaproject.service","c":"BudgetService","l":"findBudgetByCategoryAndDate(Category, LocalDate)","u":"findBudgetByCategoryAndDate(com.fdmgroup.javaproject.model.Category,java.time.LocalDate)"},{"p":"com.fdmgroup.javaproject.repository","c":"TransactionRepository","l":"findByAccount(Account)","u":"findByAccount(com.fdmgroup.javaproject.model.Account)"},{"p":"com.fdmgroup.javaproject.repository","c":"BudgetRepository","l":"findByCategoryCategoryIDAndStartDateLessThanEqualAndEndDateGreaterThanEqual(int, LocalDate, LocalDate)","u":"findByCategoryCategoryIDAndStartDateLessThanEqualAndEndDateGreaterThanEqual(int,java.time.LocalDate,java.time.LocalDate)"},{"p":"com.fdmgroup.javaproject.repository","c":"CategoryRepository","l":"findByCategoryName(String)","u":"findByCategoryName(java.lang.String)"},{"p":"com.fdmgroup.javaproject.repository","c":"UserRepository","l":"findByEmail(String)","u":"findByEmail(java.lang.String)"},{"p":"com.fdmgroup.javaproject.service","c":"AccountService","l":"findById(int)"},{"p":"com.fdmgroup.javaproject.service","c":"CategoryService","l":"findById(int)"},{"p":"com.fdmgroup.javaproject.repository","c":"BudgetRepository","l":"findByStartDateBetween(LocalDate, LocalDate)","u":"findByStartDateBetween(java.time.LocalDate,java.time.LocalDate)"},{"p":"com.fdmgroup.javaproject.repository","c":"AccountRepository","l":"findByUser(User)","u":"findByUser(com.fdmgroup.javaproject.model.User)"},{"p":"com.fdmgroup.javaproject.repository","c":"BudgetRepository","l":"findByUser(User)","u":"findByUser(com.fdmgroup.javaproject.model.User)"},{"p":"com.fdmgroup.javaproject.repository","c":"TransactionRepository","l":"findByUser(User)","u":"findByUser(com.fdmgroup.javaproject.model.User)"},{"p":"com.fdmgroup.javaproject.repository","c":"TransactionRepository","l":"findByUserAndDateBetweenAndType(User, LocalDate, LocalDate, String)","u":"findByUserAndDateBetweenAndType(com.fdmgroup.javaproject.model.User,java.time.LocalDate,java.time.LocalDate,java.lang.String)"},{"p":"com.fdmgroup.javaproject.repository","c":"TransactionRepository","l":"findByUserAndMonthAndYear(User, int, int)","u":"findByUserAndMonthAndYear(com.fdmgroup.javaproject.model.User,int,int)"},{"p":"com.fdmgroup.javaproject.repository","c":"UserRepository","l":"findByUsername(String)","u":"findByUsername(java.lang.String)"},{"p":"com.fdmgroup.javaproject.service","c":"TransactionService","l":"findTransactionById(int)"},{"p":"com.fdmgroup.javaproject.service","c":"TransactionService","l":"findTransactionsForAccount(Account)","u":"findTransactionsForAccount(com.fdmgroup.javaproject.model.Account)"},{"p":"com.fdmgroup.javaproject.service","c":"TransactionService","l":"findTransactionsForMonthAndYear(User, int, int)","u":"findTransactionsForMonthAndYear(com.fdmgroup.javaproject.model.User,int,int)"},{"p":"com.fdmgroup.javaproject.service","c":"UserService","l":"findUser(int)"},{"p":"com.fdmgroup.javaproject.service","c":"UserService","l":"findUser(String)","u":"findUser(java.lang.String)"},{"p":"com.fdmgroup.javaproject.model","c":"Transaction","l":"getAccount()"},{"p":"com.fdmgroup.javaproject.model","c":"Account","l":"getAccountID()"},{"p":"com.fdmgroup.javaproject.model","c":"Account","l":"getAccountName()"},{"p":"com.fdmgroup.javaproject.model","c":"Budget","l":"getActualSpending()"},{"p":"com.fdmgroup.javaproject.service","c":"AccountService","l":"getAllAccounts()"},{"p":"com.fdmgroup.javaproject.service","c":"BudgetService","l":"getAllBudgets()"},{"p":"com.fdmgroup.javaproject.service","c":"AccountService","l":"getAllByUser(User)","u":"getAllByUser(com.fdmgroup.javaproject.model.User)"},{"p":"com.fdmgroup.javaproject.service","c":"BudgetService","l":"getAllByUser(User)","u":"getAllByUser(com.fdmgroup.javaproject.model.User)"},{"p":"com.fdmgroup.javaproject.service","c":"TransactionService","l":"getAllByUser(User)","u":"getAllByUser(com.fdmgroup.javaproject.model.User)"},{"p":"com.fdmgroup.javaproject.service","c":"CategoryService","l":"getAllCategories()"},{"p":"com.fdmgroup.javaproject.service","c":"TransactionService","l":"getAllTransactions()"},{"p":"com.fdmgroup.javaproject.model","c":"Transaction","l":"getAmount()"},{"p":"com.fdmgroup.javaproject.model","c":"Account","l":"getBalance()"},{"p":"com.fdmgroup.javaproject.model","c":"Budget","l":"getBudgetID()"},{"p":"com.fdmgroup.javaproject.service","c":"BudgetService","l":"getBudgetsByMonthAndYear(Integer, Integer)","u":"getBudgetsByMonthAndYear(java.lang.Integer,java.lang.Integer)"},{"p":"com.fdmgroup.javaproject.service","c":"BudgetService","l":"getBudgetsByUserByMonthAndYear(Integer, Integer, User)","u":"getBudgetsByUserByMonthAndYear(java.lang.Integer,java.lang.Integer,com.fdmgroup.javaproject.model.User)"},{"p":"com.fdmgroup.javaproject.model","c":"Budget","l":"getCategory()"},{"p":"com.fdmgroup.javaproject.model","c":"Transaction","l":"getCategory()"},{"p":"com.fdmgroup.javaproject.model","c":"Category","l":"getCategoryID()"},{"p":"com.fdmgroup.javaproject.model","c":"Category","l":"getCategoryName()"},{"p":"com.fdmgroup.javaproject.model","c":"Transaction","l":"getDate()"},{"p":"com.fdmgroup.javaproject.model","c":"Transaction","l":"getDescription()"},{"p":"com.fdmgroup.javaproject.model","c":"User","l":"getEmail()"},{"p":"com.fdmgroup.javaproject.model","c":"Budget","l":"getEndDate()"},{"p":"com.fdmgroup.javaproject.model","c":"User","l":"getFirstName()"},{"p":"com.fdmgroup.javaproject.service","c":"AccountService","l":"getInitialAccountBalanceForUser(User)","u":"getInitialAccountBalanceForUser(com.fdmgroup.javaproject.model.User)"},{"p":"com.fdmgroup.javaproject.model","c":"Account","l":"getInitialBalance()"},{"p":"com.fdmgroup.javaproject.model","c":"User","l":"getLastName()"},{"p":"com.fdmgroup.javaproject.model","c":"User","l":"getPassword()"},{"p":"com.fdmgroup.javaproject.model","c":"Budget","l":"getStartDate()"},{"p":"com.fdmgroup.javaproject.model","c":"Budget","l":"getTargetAmount()"},{"p":"com.fdmgroup.javaproject.service","c":"AccountService","l":"getTotalAccountBalanceForUser(User)","u":"getTotalAccountBalanceForUser(com.fdmgroup.javaproject.model.User)"},{"p":"com.fdmgroup.javaproject.service","c":"TransactionService","l":"getTotalTransactionsByMonthAndType(User, YearMonth)","u":"getTotalTransactionsByMonthAndType(com.fdmgroup.javaproject.model.User,java.time.YearMonth)"},{"p":"com.fdmgroup.javaproject.model","c":"Transaction","l":"getTransactionID()"},{"p":"com.fdmgroup.javaproject.model","c":"Transaction","l":"getType()"},{"p":"com.fdmgroup.javaproject.model","c":"Account","l":"getUser()"},{"p":"com.fdmgroup.javaproject.model","c":"Budget","l":"getUser()"},{"p":"com.fdmgroup.javaproject.model","c":"Transaction","l":"getUser()"},{"p":"com.fdmgroup.javaproject.model","c":"User","l":"getUserID()"},{"p":"com.fdmgroup.javaproject.model","c":"User","l":"getUsername()"},{"p":"com.fdmgroup.javaproject.controller","c":"CategoryController","l":"init()"},{"p":"com.fdmgroup.javaproject","c":"JavaprojectApplication","l":"JavaprojectApplication()","u":"%3Cinit%3E()"},{"p":"com.fdmgroup.javaproject.controller","c":"UserController","l":"login()"},{"p":"com.fdmgroup.javaproject.controller","c":"UserController","l":"logout(HttpSession)","u":"logout(jakarta.servlet.http.HttpSession)"},{"p":"com.fdmgroup.javaproject","c":"JavaprojectApplication","l":"main(String[])","u":"main(java.lang.String[])"},{"p":"com.fdmgroup.javaproject.controller","c":"BudgetController","l":"processBudget(LocalDate, LocalDate, double, int, HttpSession)","u":"processBudget(java.time.LocalDate,java.time.LocalDate,double,int,jakarta.servlet.http.HttpSession)"},{"p":"com.fdmgroup.javaproject.controller","c":"UserController","l":"processLogin(String, String, HttpSession, Model)","u":"processLogin(java.lang.String,java.lang.String,jakarta.servlet.http.HttpSession,org.springframework.ui.Model)"},{"p":"com.fdmgroup.javaproject.controller","c":"DashboardController","l":"processMonthAndYearSelection(String, Integer, Integer, HttpSession, RedirectAttributes)","u":"processMonthAndYearSelection(java.lang.String,java.lang.Integer,java.lang.Integer,jakarta.servlet.http.HttpSession,org.springframework.web.servlet.mvc.support.RedirectAttributes)"},{"p":"com.fdmgroup.javaproject.controller","c":"UserController","l":"processRegistration(String, String, String, String, String, Model)","u":"processRegistration(java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String,org.springframework.ui.Model)"},{"p":"com.fdmgroup.javaproject.controller","c":"TransactionController","l":"processTransaction(int, double, int, LocalDate, String, String, HttpSession)","u":"processTransaction(int,double,int,java.time.LocalDate,java.lang.String,java.lang.String,jakarta.servlet.http.HttpSession)"},{"p":"com.fdmgroup.javaproject.controller","c":"UserController","l":"register()"},{"p":"com.fdmgroup.javaproject.service","c":"UserService","l":"registerNewUser(User)","u":"registerNewUser(com.fdmgroup.javaproject.model.User)"},{"p":"com.fdmgroup.javaproject.model","c":"Transaction","l":"setAccount(Account)","u":"setAccount(com.fdmgroup.javaproject.model.Account)"},{"p":"com.fdmgroup.javaproject.model","c":"Account","l":"setAccountID(int)"},{"p":"com.fdmgroup.javaproject.model","c":"Account","l":"setAccountName(String)","u":"setAccountName(java.lang.String)"},{"p":"com.fdmgroup.javaproject.model","c":"Budget","l":"setActualSpending(double)"},{"p":"com.fdmgroup.javaproject.model","c":"Transaction","l":"setAmount(double)"},{"p":"com.fdmgroup.javaproject.model","c":"Account","l":"setBalance(double)"},{"p":"com.fdmgroup.javaproject.model","c":"Budget","l":"setBudgetID(int)"},{"p":"com.fdmgroup.javaproject.model","c":"Budget","l":"setCategory(Category)","u":"setCategory(com.fdmgroup.javaproject.model.Category)"},{"p":"com.fdmgroup.javaproject.model","c":"Transaction","l":"setCategory(Category)","u":"setCategory(com.fdmgroup.javaproject.model.Category)"},{"p":"com.fdmgroup.javaproject.model","c":"Category","l":"setCategoryID(int)"},{"p":"com.fdmgroup.javaproject.model","c":"Category","l":"setCategoryName(String)","u":"setCategoryName(java.lang.String)"},{"p":"com.fdmgroup.javaproject.model","c":"Transaction","l":"setDate(LocalDate)","u":"setDate(java.time.LocalDate)"},{"p":"com.fdmgroup.javaproject.model","c":"Transaction","l":"setDescription(String)","u":"setDescription(java.lang.String)"},{"p":"com.fdmgroup.javaproject.model","c":"User","l":"setEmail(String)","u":"setEmail(java.lang.String)"},{"p":"com.fdmgroup.javaproject.model","c":"Budget","l":"setEndDate(LocalDate)","u":"setEndDate(java.time.LocalDate)"},{"p":"com.fdmgroup.javaproject.model","c":"User","l":"setFirstName(String)","u":"setFirstName(java.lang.String)"},{"p":"com.fdmgroup.javaproject.model","c":"Account","l":"setInitialBalance(double)"},{"p":"com.fdmgroup.javaproject.model","c":"User","l":"setLastName(String)","u":"setLastName(java.lang.String)"},{"p":"com.fdmgroup.javaproject.model","c":"User","l":"setPassword(String)","u":"setPassword(java.lang.String)"},{"p":"com.fdmgroup.javaproject.model","c":"Budget","l":"setStartDate(LocalDate)","u":"setStartDate(java.time.LocalDate)"},{"p":"com.fdmgroup.javaproject.model","c":"Budget","l":"setTargetAmount(double)"},{"p":"com.fdmgroup.javaproject.model","c":"Transaction","l":"setTransactionID(int)"},{"p":"com.fdmgroup.javaproject.model","c":"Transaction","l":"setType(String)","u":"setType(java.lang.String)"},{"p":"com.fdmgroup.javaproject.model","c":"Account","l":"setUser(User)","u":"setUser(com.fdmgroup.javaproject.model.User)"},{"p":"com.fdmgroup.javaproject.model","c":"Budget","l":"setUser(User)","u":"setUser(com.fdmgroup.javaproject.model.User)"},{"p":"com.fdmgroup.javaproject.model","c":"Transaction","l":"setUser(User)","u":"setUser(com.fdmgroup.javaproject.model.User)"},{"p":"com.fdmgroup.javaproject.model","c":"User","l":"setUserID(int)"},{"p":"com.fdmgroup.javaproject.model","c":"User","l":"setUsername(String)","u":"setUsername(java.lang.String)"},{"p":"com.fdmgroup.javaproject.controller","c":"UserController","l":"slash()"},{"p":"com.fdmgroup.javaproject.model","c":"Transaction","l":"Transaction()","u":"%3Cinit%3E()"},{"p":"com.fdmgroup.javaproject.model","c":"Transaction","l":"Transaction(LocalDate, double, String, String, Category, User, Account)","u":"%3Cinit%3E(java.time.LocalDate,double,java.lang.String,java.lang.String,com.fdmgroup.javaproject.model.Category,com.fdmgroup.javaproject.model.User,com.fdmgroup.javaproject.model.Account)"},{"p":"com.fdmgroup.javaproject.controller","c":"TransactionController","l":"TransactionController()","u":"%3Cinit%3E()"},{"p":"com.fdmgroup.javaproject.controller","c":"TransactionController","l":"transactions(Integer, Model, HttpSession)","u":"transactions(java.lang.Integer,org.springframework.ui.Model,jakarta.servlet.http.HttpSession)"},{"p":"com.fdmgroup.javaproject.service","c":"TransactionService","l":"TransactionService()","u":"%3Cinit%3E()"},{"p":"com.fdmgroup.javaproject.model","c":"Account","l":"update(Account)","u":"update(com.fdmgroup.javaproject.model.Account)"},{"p":"com.fdmgroup.javaproject.model","c":"Budget","l":"update(Budget)","u":"update(com.fdmgroup.javaproject.model.Budget)"},{"p":"com.fdmgroup.javaproject.model","c":"Category","l":"update(Category)","u":"update(com.fdmgroup.javaproject.model.Category)"},{"p":"com.fdmgroup.javaproject.model","c":"Transaction","l":"update(Transaction)","u":"update(com.fdmgroup.javaproject.model.Transaction)"},{"p":"com.fdmgroup.javaproject.model","c":"User","l":"update(User)","u":"update(com.fdmgroup.javaproject.model.User)"},{"p":"com.fdmgroup.javaproject.service","c":"AccountService","l":"updateAccount(Account)","u":"updateAccount(com.fdmgroup.javaproject.model.Account)"},{"p":"com.fdmgroup.javaproject.model","c":"User","l":"User()","u":"%3Cinit%3E()"},{"p":"com.fdmgroup.javaproject.model","c":"User","l":"User(String, String, String, String, String)","u":"%3Cinit%3E(java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String)"},{"p":"com.fdmgroup.javaproject.controller","c":"UserController","l":"UserController()","u":"%3Cinit%3E()"},{"p":"com.fdmgroup.javaproject.service","c":"UserService","l":"UserService()","u":"%3Cinit%3E()"},{"p":"com.fdmgroup.javaproject.service","c":"UserService","l":"validatePassword(String, String)","u":"validatePassword(java.lang.String,java.lang.String)"}];updateSearchResults();