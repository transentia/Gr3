// key signing information
environments {
    development {
        signingkey {
            params {
                sigfile = 'GRIFFON'
                keystore = "${basedir}/griffon-app/conf/keys/devKeystore"
                alias = 'development'
                storepass = 'BadStorePassword'
                keypass   = 'BadKeyPassword'
                lazy      = true // only sign when unsigned
            }
        }

    }
    test {
        griffon {
            jars {
                sign = false
                pack = false
            }
        }
    }
    production {
        signingkey {
            params {
                sigfile = 'GRIFFON'
                keystore = 'CHANGE ME'
                alias = 'CHANGE ME'
                // NOTE: for production keys it is more secure to rely on key prompting
                // no value means we will prompt //storepass = 'BadStorePassword'
                // no value means we will prompt //keypass   = 'BadKeyPassword'
                lazy = false // sign, regardless of existing signatures
            }
        }

        griffon {
            jars {
                sign = true
                pack = true
                destDir = "${basedir}/staging"
            }
            webstart {
                codebase = 'CHANGE ME'
            }
        }
    }
}

griffon {
    memory {
        //max = '64m'
        //min = '2m'
        //minPermSize = '2m'
        //maxPermSize = '64m'
    }
    jars {
        sign = false
        pack = false
        destDir = "${basedir}/staging"
        jarName = "${appName}.jar"
    }
    extensions {
        jarUrls = []
        jnlpUrls = []
        /*
        props {
            someProperty = 'someValue'
        }
        resources {
            linux { // windows, macosx, solaris
                jars = []
                nativelibs = []
                props {
                    someProperty = 'someValue'
                }
            }
        }
        */
    }
    webstart {
        codebase = "${new File(griffon.jars.destDir).toURI().toASCIIString()}"
        jnlp = 'application.jnlp'
    }
    applet {
        jnlp = 'applet.jnlp'
        html = 'applet.html'
    }
}

// required for custom environments
signingkey {
    params {
        def env = griffon.util.Environment.current.name
        sigfile = 'GRIFFON-' + env
        keystore = "${basedir}/griffon-app/conf/keys/${env}Keystore"
        alias = env
        // storepass = 'BadStorePassword'
        // keypass   = 'BadKeyPassword'
        lazy      = true // only sign when unsigned
    }
}

griffon {
    doc {
        logo = '<a href="http://griffon.codehaus.org" target="_blank"><img alt="The Griffon Framework" src="../img/griffon.png" border="0"/></a>'
        sponsorLogo = "<br/>"
        footer = "<br/><br/>Made with Griffon (@griffon.version@)"
    }
}

deploy {
    application {
        title = "${appName} ${appVersion}"
        vendor = System.properties['user.name']
        homepage = "http://localhost/${appName}"
        description {
            complete = "${appName} ${appVersion}"
            oneline  = "${appName} ${appVersion}"
            minimal  = "${appName} ${appVersion}"
            tooltip  = "${appName} ${appVersion}"
        }
        icon {
            'default' {
                name   = 'griffon-icon-64x64.png'
                width  = '64'
                height = '64'
            }
            splash {
                name   = 'griffon.png'
                width  = '391'
                height = '123'
            }
            selected {
                name   = 'griffon-icon-64x64.png'
                width  = '64'
                height = '64'
            }
            disabled {
                name   = 'griffon-icon-64x64.png'
                width  = '64'
                height = '64'
            }
            rollover {
                name   = 'griffon-icon-64x64.png'
                width  = '64'
                height = '64'
            }
            shortcut {
                name   = 'griffon-icon-64x64.png'
                width  = '64'
                height = '64'
            }
        }
    }
}

/*
Http dependencies from the Rest plugin are not being added to runtime.
This appears to be a problem with the dependency manager as it also defines
Http dependencies for the build itself, but with some exclusions.
The workaround is to add all dependencies explicitly in the application's
 dependency DSL (in BuildConfig.groovy) like this:
 */

griffon.project.dependency.resolution = {
    inherits "global"
    log "warn"
    repositories {
        griffonHome()
        mavenCentral()
    }
    dependencies {
        compile('org.codehaus.groovy.modules.http-builder:http-builder:0.5.2') {
            exclude group: 'org.apache.httpcomponents', module: 'httpclient'
            exclude group: 'net.sf.json-lib',           module: 'json-lib'
            exclude group: 'org.codehaus.groovy',       module: 'groovy'
            exclude group: 'net.sourceforge.nekohtml',  module: 'nekohtml'
            exclude group: 'xml-resolver',              module: 'xml-resolver'
            exclude group: 'oauth.signpost',            module: 'signpost-commonshttp4'
            exclude group: 'oauth.signpost',            module: 'signpost-core'
            exclude group: 'xerces',                    module: 'xercesImpl'
            exclude group: 'log4j',                     module: 'log4j'
        }
        compile('net.sf.json-lib:json-lib:2.4:jdk15') {
            exclude group: 'commons-beanutils',   module: 'commons-beanutils'
            exclude group: 'commons-collections', module: 'commons-collections'
            exclude group: 'commons-lang',        module: 'commons-lang'
            exclude group: 'commons-logging',     module: 'commons-logging'
            exclude group: 'xom',                 module: 'xom'
            exclude group: 'oro',                 module: 'oro'
            exclude group: 'net.sf.ezmorph',      module: 'ezmorph'
            exclude group: 'log4j',               module: 'log4j'
            exclude group: 'org.codehaus.groovy', module: 'groovy-all'
        }
        compile('net.sf.ezmorph:ezmorph:1.0.6') {
            exclude group: 'commons-beanutils',   module: 'commons-beanutils'
            exclude group: 'commons-lang',        module: 'commons-lang'
            exclude group: 'commons-logging',     module: 'commons-logging'
            exclude group: 'log4j',               module: 'log4j'
        }
        compile('commons-beanutils:commons-beanutils:1.8.0') {
            exclude group: 'commons-logging',     module: 'commons-logging'
            exclude group: 'commons-collections', module: 'commons-collections'
        }
        compile('org.apache.httpcomponents:httpclient:4.1.2') {
            exclude group: 'org.apache.httpcomponents', module: 'httpcore'
            exclude group: 'commons-logging',           module: 'commons-logging'
            exclude group: 'commons-codec',             module: 'commons-codec'
        }
        compile 'commons-collections:commons-collections:3.2.1',
                'commons-codec:commons-codec:1.6',
                'org.apache.httpcomponents:httpcore:4.1.2',
                'xml-resolver:xml-resolver:1.2',
                'commons-lang:commons-lang:2.6'
        compile('xerces:xercesImpl:2.9.1') {
            exclude group: 'xml-apis',     module: 'xml-apis'
            exclude group: 'xml-resolver', module: 'xml-resolver'
        }
    }
}

//griffon.project.dependency.resolution = {
//    // inherit Griffon' default dependencies
//    inherits("global") {
//    }
//    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
//    repositories {
//        griffonHome()
//
//        // uncomment the below to enable remote dependency resolution
//        // from public Maven repositories
//        //mavenLocal()
//        //mavenCentral()
//        //mavenRepo "http://snapshots.repository.codehaus.org"
//        //mavenRepo "http://repository.codehaus.org"
//        //mavenRepo "http://download.java.net/maven/2/"
//        //mavenRepo "http://repository.jboss.com/maven2/"
//    }
//    dependencies {
//        // specify dependencies here under either 'build', 'compile', 'runtime' or 'test' scopes eg.
//
//        // runtime 'mysql:mysql-connector-java:5.1.5'
//    }
//}

log4j = {
    // Example of changing the log pattern for the default console
    // appender:
    appenders {
        console name: 'stdout', layout: pattern(conversionPattern: '%d [%t] %-5p %c - %m%n')
    }

    error 'org.codehaus.griffon',
          'org.springframework',
          'org.apache.karaf',
          'groovyx.net'
    warn  'griffon'
}

