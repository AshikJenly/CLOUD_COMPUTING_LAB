#include<iostream>
#include<string.h>

using namespace std;
int main()
{
    string password,actualpassword;
    int a,b;
    cout<<"Enter password : ";cin>>actualpassword;

    cout<<"Enter two numbers to add : ";cin>>a>>b;
    
    passwordcheck:
        cout<<"Enter your password : ";cin>>password;
    
        if(password.compare(actualpassword) == 0)
            cout<<"Sum of numbers are : "<<a + b;
        else
        {
            cout<<"Wrong password"<<endl;
            goto passwordcheck;
        }
    return 0;

}