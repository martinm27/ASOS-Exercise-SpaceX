package com.acutisbits.asosspacex.data.usecase

import com.acutisbits.asosspacex.core.usecase.QueryUseCase
import com.acutisbits.asosspacex.data.model.api.APICompanyInfo
import com.acutisbits.asosspacex.data.model.domain.CompanyInfo
import com.acutisbits.asosspacex.data.network.ASOSSpaceXService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class QueryCompanyInfo(private val service: ASOSSpaceXService) : QueryUseCase<CompanyInfo> {

    override fun invoke(): Flow<CompanyInfo> =
        flow {
            emit(mapToReadableData(service.getCompanyInfo()))
        }.catch {
            emit(CompanyInfo.EMPTY)
        }

    private fun mapToReadableData(response: Response<APICompanyInfo>): CompanyInfo =
        if (response.isSuccessful) {
            response.body()?.let { mapToCompanyInfo(it) } ?: CompanyInfo.EMPTY
        } else {
            CompanyInfo.EMPTY
        }

    private fun mapToCompanyInfo(apiCompanyInfo: APICompanyInfo): CompanyInfo =
        with(apiCompanyInfo) {
            CompanyInfo(
                name,
                founder,
                foundationYear.toString(),
                employeesNumber,
                launchSites,
                valuation.toString()
            )
        }
}
