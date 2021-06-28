package dev.adventure.daos.claim_daos;

import dev.adventure.entities.Claim;
import dev.adventure.entities.Plan;
import dev.adventure.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClaimDaoPostgres implements ClaimDao {

    @Override
    public Claim createClaim(Claim claim) {
        try (Connection connection = ConnectionUtil.createConnection()) {
            String sql = "insert into claim values(default, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setLong(1, claim.getDate());
            ps.setFloat(2, claim.getAmount());
            ps.setString(3, claim.getReason());
            ps.setString(4, claim.getStatus());
            ps.setInt(5,claim.getUserId());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int key = rs.getInt(1);
            claim.setId(key);
            return claim;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }


    @Override
    public ArrayList<Claim> getAllClaims() {
        try (Connection connection = ConnectionUtil.createConnection()) {
            String sql = "select * from claim";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ArrayList<Claim> claims = new ArrayList<Claim>();
            while (rs.next()) {
                Claim claim = new Claim(
                        rs.getInt("id"),
                        rs.getLong("claim_date"),
                        rs.getFloat("amount"),
                        rs.getString("reason"),
                        rs.getString("status"),
                        rs.getInt("user_id")
                );
                claims.add(claim);
            }
            return claims;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }

    }
    }

