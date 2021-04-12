# Run Spring Boot in Tomcat

This repository shows how to create a WAR file from a Spring Boot 2.4 project for deployment in Tomcat.  Please read [Deploy a Spring Boot Application into Tomcat](https://developer.okta.com/blog/2019/04/16/spring-boot-tomcat) to see how this example was created.

**Prerequisites:** [Java 11](https://adoptopenjdk.net/).

> [Okta](https://developer.okta.com/) has Authentication and User Management APIs that reduce development time with instant-on, scalable user infrastructure. Okta's intuitive API and expert support make it easy for developers to authenticate, manage, and secure users and roles in any application.

* [Getting Started](#getting-started)
* [Links](#links)
* [Help](#help)
* [License](#license)

## Getting Started

To install this example, run the following commands:

```bash
git clone https://github.com/oktadeveloper/okta-spring-boot-tomcat-example.git
cd okta-spring-boot-tomcat-example
```

To run this example, you'll need to create an account and OIDC app on Okta.

### Create an Application in Okta

Before you begin, you’ll need a free Okta developer account. Install the [Okta CLI](https://cli.okta.com/) and run `okta register` to sign up for a new account. If you already have an account, run `okta login`.

Then, run `okta apps create`. Select the default app name, or change it as you see fit. Choose **Web** and press **Enter**.

Select **Okta Spring Boot Starter**. Change the Redirect URI to `http://localhost:8080/login/oauth2/code/okta,http://localhost:8080/demo/login/oauth2/code/okta` and the Logout Redirect to `http://localhost:8080/,http://localhost:8080/demo`.

After modifying this file, start the example and you should be able to authenticate with Okta at `http://localhost:8080`.

### Packaging

To package the project (and create the WAR) run the following:

```bash
./mvnw package
```

The WAR file will be created in the `target` directory. Upload `demo.war` to Tomcat and you'll be able to log in at `http://localhost:8080/demo`.

## Links

This example uses the following open source libraries:

* [Okta Spring Boot Starter](https://github.com/okta/okta-spring-boot) 
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Spring Security](https://spring.io/projects/spring-security)
* [OpenJDK](https://openjdk.java.net/)

## Help

Please post any questions as comments on the [blog post](https://developer.okta.com/blog/2019/04/16/spring-boot-tomcat), or on the [Okta Developer Forums](https://devforum.okta.com/).

## License

Apache 2.0, see [LICENSE](LICENSE).
