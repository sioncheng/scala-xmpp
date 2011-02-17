package org.simbit.xmpp
{
	package protocol.message
	{
		import scala.collection._
		import scala.xml._
		
		import org.simbit.xmpp.protocol._
		import org.simbit.xmpp.protocol.Protocol._
		
		object Headline
		{
			val messageType = MessageTypeEnumeration.Headline
			val messageTypeName = messageType.toString // FIXME, this should be done automatically via implicit def, but does not work for enum values for some reason			
			
			def apply(to:JID, from:JID, body:Option[String]):Headline = apply(None, to, from, None, body, None, None)
			
			def apply(id:Option[String], to:JID, from:JID, body:Option[String]):Headline = apply(id, to, from, None, body, None, None)
				
			def apply(id:Option[String], to:JID, from:JID, subject:Option[String], body:Option[String]):Headline = apply(id, to, from, subject, body, None, None)
					
			def apply(id:Option[String], to:JID, from:JID, subject:Option[String], body:Option[String], thread:Option[String], extensions:Option[Seq[Extension]]):Headline =
			{
				val xml = Message.build(messageType, id, to, from, subject, body, thread, extensions)
				return apply(xml)
			}
			
			def apply(xml:Node):Headline = new Headline(xml)
			
			def unapply(headline:Headline):Option[(Option[String], JID, JID, Option[String], Option[String], Option[String], Option[Seq[Extension]])] = Some(headline.id, headline.to, headline.from, headline.subject, headline.body, headline.thread, headline.extensions)
		}
		
		class Headline(xml:Node) extends Message(xml, Headline.messageType)
		{
		}
		
	}
}