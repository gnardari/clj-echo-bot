# Clojure Facebook Messenger Bot Example

This is a simple example of how to get a bot running on a Facebook page using Clojure.


## Tutorial

### 1. Create a Facebook Page

Click the link to [create a Facebook Page][createFbPage], choose a page type
and click "Get Started".

![createpage](https://cloud.githubusercontent.com/assets/7760/15977054/e3fdaf62-2f2c-11e6-9f58-b5b3de307bbc.png)

Click a bunch of "Skip"s.

![pagesetup](https://cloud.githubusercontent.com/assets/7760/15977140/5e382528-2f2d-11e6-9e21-d2cadc055be7.png)

![pagesetup2](https://cloud.githubusercontent.com/assets/7760/15977180/a1f3a54e-2f2d-11e6-9073-ecf8421e1c4c.png)

![pagesetup3](https://cloud.githubusercontent.com/assets/7760/15977225/e141bd1c-2f2d-11e6-9540-de3c70f462bb.png)



### 2. Create a Facebook App and generate a page token

Click the link to [create a Facebook App][createFbApp], then click
"Skip and Create App", and fill the form to get an App ID and procede to the
dashboard page:

![createfbapp](https://cloud.githubusercontent.com/assets/7760/15976723/e9bc75e8-2f2a-11e6-9755-29280cc27e0e.png)

On the Dashboard page of your FB App, add the "Messenger" product:

![addmessengerproduct](https://cloud.githubusercontent.com/assets/7760/15976814/7befa6b0-2f2b-11e6-8e40-41f47aa5ec36.png)

Click Settings, select the page and generate a page token

![screen shot 2016-06-10 at 5 19 27 pm](https://cloud.githubusercontent.com/assets/7760/15977534/b2b62c60-2f2f-11e6-9a4f-d902b941f9cc.png)

### 3. Setup environment variables

```shell
cp .env_sample .env

export PAGE_ACCESS_TOKEN='PASTE_YOUR_PAGE_TOKEN_HERE'
export VALIDATION_TOKEN='TYPE_ANY_RANDOM_STRING_HERE'

source .env
```

You can generate a random string in the command line with:
```shell
openssl rand -base64 36
```

![screen shot 2016-06-11 at 12 13 14 am](https://cloud.githubusercontent.com/assets/7760/15982833/5813efec-2f69-11e6-9007-a184c84ebdf9.png)

Launch your server:

```shell
lein ring server
```

### 4. Expose your machine to the world using ngrok

If you dont have it, [download ngrok][ngrokdownload].


On a different terminal launch ngrok to tunnel the port your bot is running to
a public HTTPS url:

```shell
ngrok http 3000
```

(your ngrok syntax may vary, this example uses version 2.0.25)

This will give you an https URL, to be used in the next step.

![screen shot 2016-06-10 at 5 32 36 pm](https://cloud.githubusercontent.com/assets/7760/15977893/65dfba94-2f31-11e6-9680-02b4cc7fda12.png)


### 5. Go back to your FB App Dashboard page and validate the webhook

On the dashboard page of you facebook app click "Setup Webhooks":

![webhook1](https://cloud.githubusercontent.com/assets/7760/15982810/d0b852a4-2f68-11e6-8721-99429c6db9d9.png)

Use the https url from ngrok with the callback path and the random string
verify token to fill the form. Check all checkboxes:

![webhook2](https://cloud.githubusercontent.com/assets/7760/15982857/42978c9a-2f6a-11e6-8863-cee2eb25235b.png)

### 6. Say hello

Open your page and send a message to it. If you follow all steps you should have an echo bot running.

![sayhello](https://cloud.githubusercontent.com/assets/7760/15982965/a4652b7c-2f6e-11e6-8f07-e54e30c18fbb.png)


[createFbApp]: https://developers.facebook.com/quickstarts/?platform=web
[createFbPage]: https://www.facebook.com/pages/create
[fillintheblanks]: https://github.com/fczuardi/fbbotexample/blob/master/index.js#L3-L4
[ngrokdownload]: https://ngrok.com/download

Tutorial adapted from https://github.com/fczuardi/fbbotexample
