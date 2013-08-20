/**
 *    Copyright 2013 Robert Cooper
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.totsp.tom.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: keber_000
 * Date: 8/12/13
 * Time: 7:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class EvaluationException extends RuntimeException {

    public EvaluationException(String message, Throwable cause){
        super(message, cause);
    }
}
