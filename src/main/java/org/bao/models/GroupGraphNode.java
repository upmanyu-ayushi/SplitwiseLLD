package org.bao.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GroupGraphNode {
    User lender;
    User borrower;
    int amount;
    String currency;
}
