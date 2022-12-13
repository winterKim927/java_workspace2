package network.multi.katalk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import network.domain.ChatMember;
import network.util.DBManager;

public class MysqlChatMemberDAO implements ChatMemberDAO{
	DBManager manager = DBManager.getInstance();

	public int insert(ChatMember chatMember) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "insert into chatmember(id, pass, name) values(?, ?, ?)";
		con = manager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, chatMember.getId());
			pstmt.setString(2, chatMember.getPass());
			pstmt.setString(3, chatMember.getName());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int update(ChatMember chatMember) {
		return 0;
	}

	public int delete(int chatMember_idx) {
		return 0;
	}

	public List selectAll(ChatMember chatMember) {
		return null;
	}

	public ChatMember select(int chatMember_idx) {
		return null;
	}

}
