package org.bao.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Expense {

    String id;
    Map<String, Balance> userIdToBalance;
    String groupId;
}
