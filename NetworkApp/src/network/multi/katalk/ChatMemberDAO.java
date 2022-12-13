package network.multi.katalk;

import java.util.List;

import network.domain.ChatMember;

public interface ChatMemberDAO {
	public int insert(ChatMember chatMember); 
	public int update(ChatMember chatMember);
	public int delete(int chatMember_idx);

	public List selectAll(ChatMember chatMember);
	public ChatMember select(int chatMember_idx);
}
