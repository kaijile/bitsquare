/*
 * This file is part of bisq.
 *
 * bisq is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 *
 * bisq is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with bisq. If not, see <http://www.gnu.org/licenses/>.
 */

package io.bisq.core.payment;

import io.bisq.core.payment.payload.PaymentAccountPayload;
import io.bisq.core.payment.payload.PaymentMethod;
import io.bisq.core.payment.payload.SpecificBanksAccountPayload;

import java.util.ArrayList;

public final class SpecificBanksAccount extends CountryBasedPaymentAccount implements BankNameRestrictedBankAccount, SameCountryRestrictedBankAccount {
    public SpecificBanksAccount() {
        super(PaymentMethod.SPECIFIC_BANKS);
    }

    @Override
    protected PaymentAccountPayload getPayload() {
        return new SpecificBanksAccountPayload(paymentMethod.getId(), paymentMethod.getMaxTradePeriod());
    }

    public ArrayList<String> getAcceptedBanks() {
        return ((SpecificBanksAccountPayload) paymentAccountPayload).getAcceptedBanks();
    }

    @Override
    public String getBankId() {
        return ((SpecificBanksAccountPayload) paymentAccountPayload).getBankId();
    }

    @Override
    public String getCountryCode() {
        return getCountry() != null ? getCountry().code : "";
    }
}