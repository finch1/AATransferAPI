package com.AATransferAPI.AATransferAPI.ModelAccount;

import com.AATransferAPI.AATransferAPI.API.AccountController;
import com.AATransferAPI.AATransferAPI.API.TransfersController;
import com.AATransferAPI.AATransferAPI.ModelTransfer.Transfer;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Configuration
public class AccountModelAssembler implements RepresentationModelAssembler<Account, EntityModel<Account>> {

    // .add defined in the RepresentationModel class. Add HAL Links
    @Override
    public EntityModel<Account> toModel(Account account) {
        EntityModel<Account> accountModel = EntityModel.of(account);

        // Conditional links based on state of the order.
        // To show that an account can be reactivated
        if (account.get_status() == AccountStatus.BLOCKED) {
            // request one account providing accountID
            accountModel.add(linkTo(methodOn(AccountController.class)
                    .reactivateAccount(account.get_accountID()))
                    .withRel("collection"));
        }

        // request one account providing accountID
        accountModel.add(linkTo(methodOn(AccountController.class)
                .getAccountByID(account.get_accountID()))
                .withSelfRel());

        // request all accounts for user
        accountModel.add(linkTo(methodOn(AccountController.class)
                .getAllAccountsForUser(account.get_userID()))
                .withRel("collection"));

        // make transfer
        accountModel.add(linkTo(methodOn(TransfersController.class)
                .makeTransfer(new Transfer()))
                .withRel("collection"));

        return accountModel;
    }
}
