package luyen.ninh.wallpaperx.domain.usecase

import luyen.ninh.wallpaperx.domain.usecase.LocationUC

/**
 * Created by luyen_ninh on 2019-10-13.
 */
data class UseCase(val locationUC: LocationUC,
                   val stepUC: StepUC
                    // define another usecase
                   )