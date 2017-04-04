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

package io.bisq.core.dao.blockchain;

import ch.qos.logback.classic.Level;
import io.bisq.common.app.Log;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.net.URL;

@Slf4j
public class BsqBlockchainRpcServiceMain {
    public static void main(String[] args) throws BsqBlockchainException {
        Log.setup(System.getProperty("user.home") + File.separator + "BlockchainRpcServiceMain");
        Log.setLevel(Level.WARN);

        // regtest uses port 18332, mainnet 8332
        final String rpcUser = args[0];
        final String rpcPassword = args[1];
        final String rpcPort = args[2];
        final String rpcBlockPort = args.length > 3 ? args[3] : "";
        final String rpcWalletPort = args.length > 4 ? args[4] : "";
        BsqBlockchainRpcService blockchainRpcService = new BsqBlockchainRpcService(rpcUser, rpcPassword,
                rpcPort, rpcBlockPort, rpcWalletPort);
        final URL resource = blockchainRpcService.getClass().getClassLoader().getResource("");
        final String path = resource != null ? resource.getFile() : "";
        log.info("path for BsqUTXOMap=" + path);
        BsqBlockchainManager bsqBlockchainManager = new BsqBlockchainManager(blockchainRpcService,
                new File(path));
        bsqBlockchainManager.onAllServicesInitialized(log::error);

        while (true) {
        }
    }
}