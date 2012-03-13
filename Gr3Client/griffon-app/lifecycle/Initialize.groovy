/*
 * This script is executed inside the EDT, so be sure to
 * call long running code in another thread.
 *
 * You have the following options
 * - SwingBuilder.doOutside { // your code  }
 * - Thread.start { // your code }
 * - SwingXBuilder.withWorker( start: true ) {
 *      onInit { // initialization (optional, runs in current thread) }
 *      work { // your code }
 *      onDone { // finish (runs inside EDT) }
 *   }
 *
 * You have the following options to run code again inside EDT
 * - SwingBuilder.doLater { // your code }
 * - SwingBuilder.edt { // your code }
 * - SwingUtilities.invokeLater { // your code }
 */

import groovy.swing.SwingBuilder

SwingBuilder.lookAndFeel('mac', 'nimbus', 'gtk', ['metal', [boldFonts: false]])


/*// For DROOLS
System.setProperty("drools.dialect.java.compiler", "JANINO")
System.setProperty("drools.dialect.mvel.strict", "false")

// configuration to assist logging REST plugin
//
import groovy.xml.DOMBuilder

def xml = """
<log4j:configuration xmlns:log4j="http://jakarta.apache.org/log4j/" debug="false">
	<appender name="console" class="org.apache.log4j.ConsoleAppender">
		<param name="Target" value="System.out" />
		<layout class="org.apache.log4j.PatternLayout">
			<param name="ConversionPattern" value="%d{ISO8601} %-5p  %c{1} - %m%n" />
		</layout>
	</appender>

	<category name="groovyx.net.http">
		<priority value="DEBUG" />
	</category>

	<!-- Use DEBUG to see basic request/response info;
	     Use TRACE to see headers for HttpURLClient. -->
	<category name="groovyx.net.http.HttpURLClient">
		<priority value="TRACE" />
	</category>

	<category name="org.apache.http">
		<priority value="INFO" />
	</category>
	<category name="org.apache.http.headers">
		<priority value="INFO" />
	</category>
	<category name="org.apache.http.wire">
		<priority value="TRACE" />
	</category>

	<root>
		<priority value="DEBUG" />
		<appender-ref ref="console" />
	</root>
</log4j:configuration>
"""

def reader  = new StringReader(xml)
def doc     = DOMBuilder.parse(reader)
new org.apache.log4j.xml.DOMConfigurator().configure(doc.documentElement)*/
